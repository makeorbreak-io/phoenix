//
//  LoginViewController.swift
//  UniQuiziOS
//
//  Created by Pedro Neto on 09/09/17.
//  Copyright © 2017 Phoenix. All rights reserved.
//

import UIKit

class LoginViewController: UIViewController {

    @IBOutlet weak var userText: UITextField!
    @IBOutlet weak var passText: UITextField!
    
    var loggedIn = false
    
    struct UserStruct : Codable {
        var username : String
        var password : String
        init(){
            username = ""
            password = ""
        }
    }
    
    func authorizeUser(user : String , pass : String ) {
        
        //Post
        
        var request = URLRequest(url: URL(string: Properties.getLoginURL())!)
        request.httpMethod = "POST"
        //let postString = "username=\(user)&password=\(pass)"
        var userObject = UserStruct()
        userObject.username = user
        userObject.password = pass
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        do{
        request.httpBody = try  JSONEncoder().encode(userObject)
        //request.httpBody = postString.data(using: .utf8)
            let task = URLSession.shared.dataTask(with: request) { data, response, error in
            guard let data = data, error == nil else {                                                 // check for fundamental networking error
                print("error=\(error)")
                return
            }
            
            if let httpStatus = response as? HTTPURLResponse, httpStatus.statusCode != 200 {           // check for http errors
                print("statusCode should be 200, but is \(httpStatus.statusCode)")
                print("response = \(response)")
                let preferences = UserDefaults.standard
                preferences.removeObject(forKey: "username")
                preferences.removeObject(forKey: "password")
                self.loggedIn = false
            }
            else {
                do{
                let responseString = String(data: data, encoding: .utf8)
                print("responseString = \(responseString)")
                var userDTO = try JSONDecoder().decode(User.self, from: data)
                DispatchQueue.main.sync {
                    let preferences = UserDefaults.standard
                    
                    
                    preferences.set(user, forKey: "username")
                    preferences.set(pass, forKey: "password")
                    //  Save to disk
                    let didSave = preferences.synchronize()

                    Properties.user = userDTO
                    self.loggedIn = true 
                    self.performSegue(withIdentifier: "loginSegue", sender: nil)
                    }
                }catch{}
                
                
            }
        }
        task.resume()
        }
        catch{}
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        self.view.endEditing(true)
    }
    
    func checkUserExistence(){
        let preferences = UserDefaults.standard
        if preferences.object(forKey: "username") == nil {
            print("Não exite predefinido")
        } else {
            let username = preferences.string(forKey: "username")
            let password =  preferences.string(forKey: "password")
            authorizeUser(user: username!, pass: password!)
            
        }
    }
    
   
    override func viewDidLoad() {
        super.viewDidLoad()
        checkUserExistence()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func shouldPerformSegue(withIdentifier identifier: String, sender: Any?) -> Bool {
        if identifier == "loginSegue"{
            authorizeUser(user : userText.text! , pass : passText.text!)
            if loggedIn {
                print("Utilizador Autorizado")
                return true
            }
            print("Utilizador Não Autorizado")
            return false
        }
        print("Outro Segue ")
        return true
    }
    
   

  

}
