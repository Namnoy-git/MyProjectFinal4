package com.it.myprojectfinal.model.body

data class BodyImageUser(var data: ArrayList<Data>) {

    class Data(



        name: String,
        img: String
    ) {


        var name = ""
        var img = ""

        init {



            this.name = name
            this.img = img
        }
    }

}
