package codes.draeger.hotels.api

import codes.draeger.TestContainer
import codes.draeger.hotels.model.Hotel
import codes.draeger.hotels.model.Review
import codes.draeger.hotels.model.Room
import codes.draeger.hotels.model.RoomStatus
import codes.draeger.hotels.repository.HotelRepository
import codes.draeger.hotels.repository.ReviewRepository
import codes.draeger.hotels.repository.RoomRepository
import codes.draeger.hotels.testdata.aDummyHotel
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
import strikt.assertions.hasSize

@SpringBootTest
@AutoConfigureMockMvc
internal class HotelControllerTest(
    @Autowired val hotelRepository: HotelRepository,
    @Autowired val roomRepository: RoomRepository,
    @Autowired val reviewRepository: ReviewRepository,
    @Autowired val mockMvc: MockMvc,
) {
    val address = HotelRequest.Address(
        street = "Ruyenzi",
        number = "21",
        zipCode = "555",
        city = "Kigali"
    )
    val aHotel = HotelRequest(name= "lys",
        address
    )
    @BeforeEach
    fun `clearing everything`(){
        hotelRepository.deleteAll()
	roomRepository.deleteAll()
	reviewRepository.deleteAll()
    }

    @Test
    fun `will get empty list of hotels by default`() {

        mockMvc.get("/all").andExpect {
            status { isOk() }
            jsonBody { emptyList<Hotel>() }
        }
    }

    @Test
    fun `can add a hotel`() {
        mockMvc.post("/add") {
            withJsonBody(aHotel)
        }.andExpect {
            status { isOk() }
        }
        expectThat(hotelRepository.findAll().map { it.name })
            .hasSize(1)
            .containsExactly("lys")

    }

    @Test
    fun `retrieve a single hotel`() {
        mockMvc.post("/add") {
            withJsonBody(aHotel)
        }.andExpect {
            status { isOk() }
        }
        expectThat(hotelRepository.findAll().map { it.name })
            .hasSize(1)
            .containsExactly("lys")

    }
     @Test
    fun `can add a review to a hotel`() {
        val testReview = Review(message = "it was nice", stars = 4)
        mockMvc.post("/add") {
            withJsonBody(aHotel)
        }
         val testHotel = hotelRepository.findAll().last();
        mockMvc.post("/review/add/${testHotel.id}") {
            withJsonBody(testReview)
        }.andExpect {
            status { isOk() }
        }
	  expectThat(reviewRepository.findAll().map { it.message })
            .hasSize(1)
            .containsExactly("it was nice")  
        

    }
    val testRoom = Room(roomNumber = 200, RoomStatus.FREE)
    @Test
    fun `can add a room to a hotel`() {
        mockMvc.post("/add") {
            withJsonBody(aHotel)
        }
        val testHotel = hotelRepository.findAll().last();
	mockMvc.post("/room/add/${testHotel.id}") {
            withJsonBody(testRoom)
        }.andExpect {
            status { isOk() }
        }
	   
        expectThat(roomRepository.findAll().map { it.roomNumber })
            .hasSize(1)
            .containsExactly(200)
    }


    @Test
    fun `can change status of a room`() {

        val testRoomStatus = RoomStatus.NEEDS_CLEANING
        mockMvc.post("/add") {
            withJsonBody(aHotel)
        }
        val testHotel = hotelRepository.findAll().find { aHotel.name == it.name };
        if (testHotel != null) {
            mockMvc.post("/room/add/${testHotel.id}") {
                withJsonBody(testRoom)
            }
            mockMvc.patch("/room/${testHotel.id}/change-status/${testRoom.roomNumber}") {
                withJsonBody(testRoomStatus)
            }.andExpect {
                status { isOk() }
            }
	     expectThat(roomRepository.findAll().map { it.status })
            .hasSize(1)
            .containsExactly(testRoomStatus)
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