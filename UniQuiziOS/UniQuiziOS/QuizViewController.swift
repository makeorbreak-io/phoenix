//
//  QuizViewController.swift
//  UniQuiziOS
//
//  Created by Pedro Neto on 08/09/17.
//  Copyright © 2017 Phoenix. All rights reserved.
//

import UIKit

class QuizViewController: UIViewController {
    
    @IBOutlet weak var rightSwipeButton: UIButton!
    @IBOutlet weak var leftSwipeButton: UIButton!
    
    @IBOutlet weak var answerFour: UIButton!
    @IBOutlet weak var answerThree: UIButton!
    @IBOutlet weak var questionLabel: UILabel!
    
    @IBOutlet weak var answerTwo: UIButton!
    @IBOutlet weak var answerOne: UIButton!
    var quizPk : Int?
    var quiz : Quiz?
    var actualPage = 0
    var revision = false
    var selectedAnswers = [Int]()
    
    
    func sendResolution(_ resolution : SolutionPost){
        //Post
        
        var request = URLRequest(url: URL(string: Properties.getPostSolutionURL())!)
        request.httpMethod = "POST"
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")

        do{
        request.httpBody = try JSONEncoder().encode(resolution)
            let task = URLSession.shared.dataTask(with: request) { data, response, error in
                guard let data = data, error == nil else {                                                 // check for fundamental networking error
                    print("error=\(error)")
                    return
                }
                
                if let httpStatus = response as? HTTPURLResponse, httpStatus.statusCode != 200 {           // check for http errors
                    print("statusCode should be 200, but is \(httpStatus.statusCode)")
                    print("response = \(response)")
                    
                }
                else {
                    let responseString = String(data: data, encoding: .utf8)
                    print("responseString = \(responseString)")
                    
                }
            }
            task.resume()
        }
        catch{
            print("Fail to encode")
        }
        
    }
    func endQuiz(){
        var resolution = SolutionPost(username : (Properties.user?.email)!, quizPk : Int((quiz?.pk)!))
        var j = 0
        for i in selectedAnswers{
            if i == 0 {
                resolution.wrongAnswers += 1
                j+=1
                continue
            }
            if (quiz?.questions[j].answers[i - 1].rightAnswer)!{
                resolution.rightAnswers += 1
            }
            else {
                resolution.wrongAnswers += 1
            }
            
            j+=1
        }
        sendResolution(resolution)
        startRevision()
    }
    
    func startRevision(){
        revision = true
        answerOne.isEnabled = false
        answerTwo.isEnabled = false
        answerThree.isEnabled = false
        answerFour.isEnabled = false
        leftSwipeButton.isEnabled = false
        actualPage = 0
       changeButtonsContentWhileOnRevision()
    }
    
    
    
    func checkIfSelected(){
        
        answerOne.backgroundColor = UIColor.white
        answerTwo.backgroundColor = UIColor.white
        answerThree.backgroundColor = UIColor.white
        answerFour.backgroundColor = UIColor.white
        switch selectedAnswers[actualPage] {
        case 1:
            answerOne.backgroundColor = UIColor.green
        case 2:
            answerTwo.backgroundColor = UIColor.green
        case 3:
            answerThree.backgroundColor = UIColor.green
        case 4:
            answerFour.backgroundColor = UIColor.green
        default:
            break
        }
    }
    
    func changeButtonsContentWhileDoingQuiz(){
        answerFour.isEnabled = false
        answerThree.isEnabled = false
        answerThree.setTitle("", for: UIControlState.disabled)
        answerFour.setTitle("", for: UIControlState.disabled)
        checkIfSelected()
        questionLabel.text  = (quiz?.questions[actualPage].question)!
        answerOne.setTitle((quiz?.questions[actualPage].answers[0].answer)!, for: UIControlState.normal)
        answerTwo.setTitle((quiz?.questions[actualPage].answers[1].answer)!, for: UIControlState.normal)
        if ((quiz?.questions[actualPage].answers.count)! > 2){answerThree.setTitle((quiz?.questions[actualPage].answers[2].answer)!, for: UIControlState.normal)
            answerThree.isEnabled = true
            if  (quiz?.questions[actualPage].answers.count)! > 3 {
                answerFour.setTitle((quiz?.questions[actualPage].answers[3].answer)!, for: UIControlState.normal)
                answerFour.isEnabled = true
            }
        }
    }
    
    func findCorrectAnswer () -> Int{
        var i = 0
        while true {
            i += 1
            if (quiz?.questions[actualPage].answers[i-1].rightAnswer)!{
                break
            }
        }
        return i
    }
    
    func markButtonAsCorrect(position : Int ){
        answerOne.backgroundColor = UIColor.white
        answerTwo.backgroundColor = UIColor.white
        answerThree.backgroundColor = UIColor.white
        answerFour.backgroundColor = UIColor.white
        switch position {
        case 1:
            answerOne.backgroundColor = UIColor.green
        case 2:
            answerTwo.backgroundColor = UIColor.green
        case 3:
            answerThree.backgroundColor = UIColor.green
        case 4:
            answerFour.backgroundColor = UIColor.green
        default:
            break
        }
    }
    
    func markNeutralAnswer(answerPos :  Int){
        markButtonAsCorrect(position: answerPos)
    }
    
    func markAsWrong (answerPos : Int ,wrong : Int){
        answerOne.backgroundColor = UIColor.white
        answerTwo.backgroundColor = UIColor.white
        answerThree.backgroundColor = UIColor.white
        answerFour.backgroundColor = UIColor.white
        switch answerPos {
        case 1:
            answerOne.backgroundColor = UIColor.green
        case 2:
            answerTwo.backgroundColor = UIColor.green
        case 3:
            answerThree.backgroundColor = UIColor.green
        case 4:
            answerFour.backgroundColor = UIColor.green
        default:
            break
        }
        switch wrong {
        case 1:
            answerOne.backgroundColor = UIColor.red
        case 2:
            answerTwo.backgroundColor = UIColor.red
        case 3:
            answerThree.backgroundColor = UIColor.red
        case 4:
            answerFour.backgroundColor = UIColor.red
        default:
            break
        }
    }
    
    func changeButtonsContentWhileOnRevision(){
        questionLabel.text  = (quiz?.questions[actualPage].question)!
        answerOne.setTitle((quiz?.questions[actualPage].answers[0].answer)!, for: UIControlState.disabled)
        answerTwo.setTitle((quiz?.questions[actualPage].answers[1].answer)!, for: UIControlState.disabled)
        if ((quiz?.questions[actualPage].answers.count)! > 2){answerThree.setTitle((quiz?.questions[actualPage].answers[2].answer)!, for: UIControlState.disabled)
            if  (quiz?.questions[actualPage].answers.count)! > 3 {
                answerFour.setTitle((quiz?.questions[actualPage].answers[3].answer)!, for: UIControlState.disabled)
                
            }
            else{
                answerFour.setTitle("", for: UIControlState.disabled)
            }
        }
        else{
            answerFour.setTitle("", for: UIControlState.disabled)
            answerThree.setTitle("", for: UIControlState.disabled)
        }
        var i = selectedAnswers[actualPage]
        if i == 0
        {
            markNeutralAnswer(answerPos :  findCorrectAnswer())

        }
        else if (quiz?.questions[actualPage].answers[i - 1].rightAnswer)!{
            markButtonAsCorrect(position : i )
        }
        else{
            markAsWrong (answerPos : findCorrectAnswer(),wrong : i)
        }
        
        
    }
    
    @IBAction func onSwipeRight(_ sender: UIButton) {
        leftSwipeButton.isEnabled = true
        actualPage += 1
        if actualPage == ((quiz?.questions.count )!  ) {
            if !revision {
                
                let alert = UIAlertController(title: "Terminar quiz", message: "O quiz será terminado", preferredStyle: .actionSheet)
                alert.addAction(UIAlertAction(title: NSLocalizedString("OK", comment: "Default action"), style: .`default`, handler: { _ in
                    self.endQuiz()
                }))
                alert.addAction(UIAlertAction(title: NSLocalizedString("Cancel", comment: "Cancel"), style: .`default`, handler: nil))
                self.present(alert, animated: true, completion: nil)
                
            }
            else {
                let alert = UIAlertController(title: "Terminar revisão", message: "A revisão será terminada", preferredStyle: .actionSheet)
                alert.addAction(UIAlertAction(title: NSLocalizedString("OK", comment: "Default action"), style: .`default`, handler: { _ in
                //self.navigationController?.popToViewController( (SearchTableViewController() as? UIViewController)!, animated: true)\
                    self.navigationController?.popViewController(animated: true)
                }))
                alert.addAction(UIAlertAction(title: NSLocalizedString("Cancel", comment: "Cancel"), style: .`default`, handler: nil))
                self.present(alert, animated: true, completion: nil)
            }
        }
        else{
            if !revision {changeButtonsContentWhileDoingQuiz()
            }
            else {
                changeButtonsContentWhileOnRevision()
            }
        }
     
    }
    
    @IBAction func onSwipeLeft(_ sender: UIButton) {
            actualPage -= 1
        if !revision {changeButtonsContentWhileDoingQuiz()}
        else {changeButtonsContentWhileOnRevision()}
        if actualPage == 0  {
            leftSwipeButton.isEnabled = false
        }
        
        
    }
    
    func fillArrayWithZero (){
        let count =  (quiz?.questions.count)!
        var i = 0
        while i < count {
            selectedAnswers.append(0)
            i+=1
        }
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        fillArrayWithZero()
        leftSwipeButton.isEnabled = false
        changeButtonsContentWhileDoingQuiz()
        answerOne.titleLabel?.lineBreakMode = NSLineBreakMode.byWordWrapping
        answerTwo.titleLabel?.lineBreakMode = NSLineBreakMode.byWordWrapping
        answerThree.titleLabel?.lineBreakMode = NSLineBreakMode.byWordWrapping
        answerFour.titleLabel?.lineBreakMode = NSLineBreakMode.byWordWrapping
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    
    @IBAction func onAnswerOneSelected(_ sender: UIButton) {
        if selectedAnswers[actualPage] != 1 {
        selectedAnswers[actualPage] = 1
        answerOne.backgroundColor = UIColor.green
        answerTwo.backgroundColor = UIColor.white
        answerThree.backgroundColor = UIColor.white
        answerFour.backgroundColor = UIColor.white
        }
        else {
            selectedAnswers[actualPage] = 0
            answerOne.backgroundColor = UIColor.white
        }
    }
    
    @IBAction func onAnswerTwoSelected(_ sender: UIButton) {
        if selectedAnswers[actualPage] != 2 {
        selectedAnswers[actualPage] = 2
        answerOne.backgroundColor = UIColor.white
        answerTwo.backgroundColor = UIColor.green
        answerThree.backgroundColor = UIColor.white
        answerFour.backgroundColor = UIColor.white
        }
        else {
            selectedAnswers[actualPage] = 0
            answerTwo.backgroundColor = UIColor.white
            
        }

    }
    
    
    @IBAction func onAnswerThreeSelected(_ sender: UIButton) {
        if selectedAnswers[actualPage] != 3 {
        selectedAnswers[actualPage] = 3
        answerOne.backgroundColor = UIColor.white
        answerTwo.backgroundColor = UIColor.white
        answerThree.backgroundColor = UIColor.green
        answerFour.backgroundColor = UIColor.white
        }
        else{
            selectedAnswers[actualPage] = 0
            answerThree.backgroundColor = UIColor.white
            
        }

    }
    
    @IBAction func onAnswerFourSelected(_ sender: UIButton) {
        if selectedAnswers[actualPage] != 4 {
        selectedAnswers[actualPage] = 4
        answerOne.backgroundColor = UIColor.white
        answerTwo.backgroundColor = UIColor.white
        answerThree.backgroundColor = UIColor.white
        answerFour.backgroundColor = UIColor.green
        }
        else {
            selectedAnswers[actualPage] = 0
            answerFour.backgroundColor = UIColor.white
            
        }

    }
    
    /*
     // MARK: - Navigation
     
     // In a storyboard-based application, you will often want to do a little preparation before navigation
     override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
     // Get the new view controller using segue.destinationViewController.
     // Pass the selected object to the new view controller.
     }
     */
    
}
