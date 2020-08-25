package com.it.myprojectfinal.model.body

data class BodyUpdateUserImage(var data: ArrayList<Data>) {

    class Data(

        userId: String,
        user_name: String,
        user_username: String,
        user_password: String,
        user_email: String,
        user_address: String,
        user_phone: String,
        name: String,
        img: String
    ) {
        var userId = ""
        var user_name = ""
        var user_username = ""
        var user_password = ""
        var user_email = ""
        var user_address = ""
        var user_phone = ""
        var name = ""
        var img = ""

        init {
            this.userId = userId
            this.user_name = user_name
            this.user_username = user_username
            this.user_password = user_password
            this.user_email = user_email
            this.user_address = user_address
            this.user_phone = user_phone
            this.name = name
            this.img = img
        }
    }

}