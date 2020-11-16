package main.login
import java.util.UUID

class Pessoa(
//        var id : String = UUID.randomUUID().toString(),
        var usuario : String,
        var senha : String
) {
    var id : String? = "id"

    init{
        id = UUID.randomUUID().toString()
    }

}