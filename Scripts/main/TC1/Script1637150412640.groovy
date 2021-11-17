import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/profile.php#login')

WebUI.setText(findTestObject('Login/Page_CURA Healthcare Service_Login/input_Username_username'), 'John Doe')

WebUI.setEncryptedText(findTestObject('Login/Page_CURA Healthcare Service_Login/input_Password_password'), 'g3/DOGG74jC3Flrr3yH+3D/yKbOqqUNM')

WebUI.click(findTestObject('Login/Page_CURA Healthcare Service_Login/button_Login'))

WebUI.closeBrowser()
