package main.br.login
import java.util.UUID

class User(
//        var id : String = UUID.randomUUID().toString(),
        var usuario : String,
        var senha : String
) {
    var id : String? = "id"

    init{
        id = UUID.randomUUID().toString()
    }

}