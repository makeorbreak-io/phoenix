//
//  QuizInfoViewController.swift
//  UniQuiziOS
//
//  Created by Pedro Neto on 09/09/17.
//  Copyright © 2017 Phoenix. All rights reserved.
//

import UIKit

class QuizInfoViewController: UIViewController {

    @IBOutlet weak var wrongAnswers: UILabel!
    @IBOutlet weak var dificultyLabel: UILabel!
    @IBOutlet weak var quizNameLabel: UILabel!
    @IBOutlet weak var righttAnswers: UILabel!
    var quiz : SimplifiedQuiz?
    var solution : Solution?
    var fullQuiz : Quiz?
    var loadedFullQuiz = false
    func getFullQuiz(){
        guard let url = URL(string: Properties.getQuizURL(quizPk: String((quiz?.pk)!))) else {return}
        print(url)
        let session = URLSession.shared
        session.dataTask(with: url) { (data, response, error) in
            guard let response = response else {
                return
            }
            print(response)
            if let data = data {
                do{
                    self.fullQuiz = try  JSONDecoder().decode(Quiz.self, from: data)
                    self.loadedFullQuiz = true
                }
                catch{
                    print("Unable to decode")
                }
            }
            }.resume()
    }
    
    func getPrevSolution(){
        guard let url = URL(string: Properties.getSolutionsURL(quizPk: String((quiz?.pk)!),user : String((Properties.user?.email)!))) else {return}
        print(url)
        let session = URLSession.shared
        session.dataTask(with: url) { (data, response, error) in
            guard let response = response else {
                return
            }
            print(response)
            if let data = data {
                do{
                    self.solution = try  JSONDecoder().decode(Solution.self, from: data)
                    DispatchQueue.main.sync {
                        if self.solution != nil{
                        self.righttAnswers.text = String((self.solution?.rightAnswers)!)
                        self.wrongAnswers.text = String((self.solution?.wrongAnswers)!)
                        }
                    }
                }
                catch{
                    print("Unable to decode")
                }
               
            }
            }.resume()
    }
    override func viewWillAppear(_ animated: Bool) {
        getPrevSolution()
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        wrongAnswers.text = "0"
        righttAnswers.text = "0"
        getPrevSolution()
        dificultyLabel.text = (quiz?.difficulty)!
        quizNameLabel.text = (quiz?.title)!
        getFullQuiz()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func shouldPerformSegue(withIdentifier identifier: String, sender: Any?) -> Bool {
        if identifier == "startQuizSegue"{
            if fullQuiz != nil {return true}
            else {return false}
        }
        return true
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "startQuizSegue" {
           guard let destination = segue.destination as? QuizViewController else { fatalError()}
            destination.quiz = fullQuiz
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
