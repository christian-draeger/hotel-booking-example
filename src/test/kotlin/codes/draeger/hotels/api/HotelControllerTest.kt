package codes.draeger.hotels.api

import codes.draeger.hotels.model.Hotel
import codes.draeger.hotels.model.aDummyHotel
import codes.draeger.hotels.repository.HotelRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*
import strikt.api.expectThat
import strikt.assertions.containsExactly

@SpringBootTest
@AutoConfigureMockMvc
internal class HotelControllerTest(
    @Autowired val hotelRepository: HotelRepository,
    @Autowired val mockMvc: MockMvc
) {

    @Test
    fun `will get empty list of hotels by default`() {
        hotelRepository.deleteAll()
        mockMvc.get("/all").andExpect {
            status { is2xxSuccessful() }
            jsonBody { emptyList<Hotel>() }
        }
    }

    @Test
    fun `can add a hotel`() {
        val testHotel = aDummyHotel()
        mockMvc.post("/add") {
            withJsonBody(testHotel)
        }.andExpect {
            status { is2xxSuccessful() }
        }
        expectThat(hotelRepository.listAll()).containsExactly(testHotel)
    }

    private fun <T> MockHttpServletRequestDsl.withJsonBody(t: T) {
        contentType = MediaType.APPLICATION_JSON
        accept = MediaType.APPLICATION_JSON
        content = jacksonObjectMapper().writeValueAsString(t)

    }

    private fun <T> MockMvcResultMatchersDsl.jsonBody(init: () -> T) {
        content { contentType(MediaType.APPLICATION_JSON) }
        content { json(jacksonObjectMapper().writeValueAsString(init())) }
    }
}