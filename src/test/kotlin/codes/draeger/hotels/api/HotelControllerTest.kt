package codes.draeger.hotels.api

import codes.draeger.hotels.model.*
import codes.draeger.hotels.repository.DummyHotelRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.BeforeEach
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
    @Autowired val hotelRepository: DummyHotelRepository,
    @Autowired val mockMvc: MockMvc,
) {
    val testHotel = aDummyHotel()
    @BeforeEach
    fun `clearing everything`(){
        hotelRepository.deleteAll()
    }

    @Test
    fun `will get empty list of hotels by default`() {

        mockMvc.get("/all").andExpect {
            status { is2xxSuccessful() }
            jsonBody { emptyList<Hotel>() }
        }
    }

    @Test
    fun `can add a hotel`() {

        mockMvc.post("/add") {
            withJsonBody(testHotel)
        }.andExpect {
            status { is2xxSuccessful() }
        }
        expectThat(hotelRepository.listAll()).containsExactly(testHotel)

    }
    @Test
    fun `retrieve a single hotel`() {
        mockMvc.post("/add") {
            withJsonBody(testHotel)
        }
        mockMvc.get("/all/${testHotel.id}").andExpect {
            status { is2xxSuccessful() }
            jsonBody { hotelRepository.listAll().find { it.id == testHotel.id } }
        }

    }
    @Test
    fun `can add a review to a hotel`() {
        val testReview = aDummyReview()
        mockMvc.post("/add") {
            withJsonBody(testHotel)
        }
        mockMvc.post("/add-review/${testHotel.id}") {
            withJsonBody(testReview)
        }.andExpect {
            status { is2xxSuccessful() }
        }
        hotelRepository.listAll().find { it.id == testHotel.id }
            ?.let { expectThat(it.reviews).containsExactly(testReview) }


    }
    @Test
    fun `can add a room to a hotel`() {
        val testRoom = aDummyRoom()
        mockMvc.post("/add") {
            withJsonBody(testHotel)
        }
        mockMvc.post("/add-room/${testHotel.id}") {
            withJsonBody(testRoom.roomNumber)
        }.andExpect {
            status { is2xxSuccessful() }
        }
        hotelRepository.listAll().find { it.id == testHotel.id }
            ?.let { expectThat(it.rooms).containsExactly(testRoom) }
        }


    @Test
    fun `can change status of a room`() {

        val testRoom = aDummyRoom()
        val testRoomStatus = RoomStatus.NEEDS_CLEANING
        mockMvc.post("/add") {
            withJsonBody(testHotel)
        }
        mockMvc.post("/add-room/${testHotel.id}") {
            withJsonBody(testRoom.roomNumber)
        }
        mockMvc.patch("/all/${testHotel.id}/change-status/${testRoom.roomNumber}") {
            withJsonBody(testRoomStatus)
        }.andExpect {
            status { is2xxSuccessful() }
        }
        hotelRepository.listAll().find { it.id == testHotel.id }?.rooms?.
        find { it.roomNumber == testRoom.roomNumber }?.
        let { expectThat(it.status).equals(testRoomStatus)}
    }

    //negative test
    @Test
    fun `passing in the status value not equal to one of the enum values`() {

        val testRoom = aDummyRoom()
        val testRoomStatus = "it's open"
        mockMvc.post("/add") {
            withJsonBody(testHotel)
        }
        mockMvc.post("/add-room/${testHotel.id}") {
            withJsonBody(testRoom)
        }
        mockMvc.patch("/all/${testHotel.id}/change-status/${testRoom.roomNumber}") {
            withJsonBody(testRoomStatus)
        }.andExpect {
            status { isEqualTo(400) }
        }

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