package com.area_b.samples

import java.text.SimpleDateFormat

class Todo {

    static transients = ["dateParser"]

    int priority
    String description
    String deadline

    static constraints = {
        priority size: 1..2, blank: false, unique: true
        description size: 1..20, blank: false
        deadline size: 1..10
    }
}


