//
//  CoursesTableViewCell.swift
//  UniQuiziOS
//
//  Created by Pedro Neto on 10/09/17.
//  Copyright © 2017 Phoenix. All rights reserved.
//

import UIKit

class CoursesTableViewCell: UITableViewCell {

    
    @IBOutlet var courseName: UILabel!
    
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
