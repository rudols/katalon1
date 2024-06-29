import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.webservice.verification.WSResponseManager as WSResponseManager

res_getbook = WS.sendRequest(findTestObject('API/RestFull Booker/GetBookingIds'))
def bookingid = WS.getElementPropertyValue(res_getbook, "[0].bookingid")

response = WS.sendRequestAndVerify(findTestObject('API/RestFull Booker/Create Token'))

ResponseObject responseobj = WSResponseManager.getInstance().getCurrentResponse()

String token = WS.getElementPropertyValue(responseobj, 'token')

WS.comment('Get Token Value, then assign to Delete Booking Request')

response2 = WS.sendRequest(findTestObject('API/RestFull Booker/Delete Booking', [('booking_id') : bookingid, ('token') : token]))

WS.verifyResponseStatusCode(response2, 201)

