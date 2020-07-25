package com.it.myprojectfinal.model.body

data class BodyImage(var data: ArrayList<Data>) {

    class Data(


        notic_id: String,
        name: String,
        img: String
    ) {

        var notic_id = ""
        var name = ""
        var img = ""

        init {


            this.notic_id = notic_id
            this.name = name
            this.img = img
        }
    }

}



