package codes.draeger.hotels.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController {

    @GetMapping("/json-example")
    fun sayHello() = MyResponseData(
        name = "chris",
        foo = listOf(
            Foo(30, 187),
            Foo(60, 210)
        )
    )

    @PostMapping("/add-data")
    fun addData(
        @RequestBody body: String
    ) = body.uppercase()
}

data class MyResponseData(
    val name: String,
    val foo: List<Foo>
)

data class Foo(
    val age: Int,
    val size: Int?
)