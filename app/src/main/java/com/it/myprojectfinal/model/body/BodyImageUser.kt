package com.it.myprojectfinal.model.body

data class BodyImageUser(var data: ArrayList<Data>) {

    class Data(


        user_name : String,
        username : String,
        password : String,
        email : String,
        address : String,
        phone : String,
        name: String,
        img: String
    ) {

        var user_name = ""
        var username = ""
        var password = ""
        var email = ""
        var address = ""
        var phone = ""
        var name = ""
        var img = ""

        init {
            this.user_name = user_name
            this.username = username
            this.password = password
            this.email = email
            this.address = address
            this.phone = phone
            this.name = name
            this.img = img
        }
    }

}
