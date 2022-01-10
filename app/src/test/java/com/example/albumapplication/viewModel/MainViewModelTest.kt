package com.example.albumapplication.viewModel

import com.albumapplication.data.apiservices.ApiClientModule
import com.albumapplication.data.apiservices.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.io.InputStreamReader
import java.net.HttpURLConnection

class MainViewModelTest {
    @Mock
    var service: ApiService = ApiClientModule.providesAPIService()
    val reader: InputStreamReader
    val content: String
    val mockWebServer = MockWebServer()

    init {
        reader =
            InputStreamReader(this.javaClass.classLoader?.getResourceAsStream("remote_album_element.json"))
        content = reader.readText()
        reader.close()
    }

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        //start mock server
        mockWebServer.start()
    }

    @Test
    fun canGettingAlbumList() = runBlocking {
        // call the api
        val response = service.getAlbumList()
        assertEquals(
            response.toString().contains("200"),
            response.code().toString().contains("200")
        )

    }

    @Test
    fun readSampleJsonFile() {
        Assert.assertNotNull(content)
    }

    @Test
    fun checkSuccessContainResponse() = runBlocking{
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(content)
        mockWebServer.enqueue(response)

        val mockResponse = response.getBody()?.readUtf8()
        val appResponse = service.getAlbumList()
        val jsonData = Gson().toJson(appResponse.body())

        assertEquals(jsonData.contains(mockResponse!!),true)
        //assertEquals(mockResponse, jsonData)
    }


    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}