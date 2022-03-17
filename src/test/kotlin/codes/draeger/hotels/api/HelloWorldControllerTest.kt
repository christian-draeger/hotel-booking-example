package codes.draeger.hotels.api

import codes.draeger.hotels.model.Address
import codes.draeger.hotels.model.Hotel
import codes.draeger.hotels.repository.HotelRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class HelloWorldControllerTest(
    @Autowired val hotelRepository: HotelRepository,
    @Autowired val mockMvc: MockMvc
) {

    @Test
    fun `will get empty list of hotels by default`() {
        mockMvc.get("/all").andExpect {
            status { is2xxSuccessful() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json("[]") }
        }
    }

    @Test
    fun `can add a hotel`() {
        val testHotel = Hotel(
            name = "test",
            address = Address(
                street = "aaa",
                number = "bbb",
                zipCode = "12345",
                city = "ccc"
            )
        )

        mockMvc.post("/add") {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(testHotel)
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { is2xxSuccessful() }
        }

        assertTrue(hotelRepository.listAll().contains(testHotel))
    }
}