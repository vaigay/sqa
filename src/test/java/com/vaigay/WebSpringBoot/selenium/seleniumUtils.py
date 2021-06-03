from selenium import webdriver
from selenium.common.exceptions import TimeoutException
from selenium.webdriver.common.alert import Alert
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.keys import Keys
from time import sleep
from sqlUtils import getIdLastUser, getUserById, getUserByIdForDel

from selenium.webdriver.support.select import Select
from selenium.webdriver.support.wait import WebDriverWait


def addUserAction(name, dob, address, email, cource, major):
    browser = webdriver.Chrome()

    browser.get("http://localhost:8080/newUser")
    dataElement = browser.find_element_by_name('fullName')
    dataElement.send_keys(name)

    dataElement = browser.find_element_by_name('dateOfBirth')
    dataElement.send_keys(dob)

    dataElement = browser.find_element_by_name('address')
    dataElement.send_keys(address)

    dataElement = browser.find_element_by_name('email')
    dataElement.send_keys(email)

    dataElement = Select(browser.find_element_by_name('course.id'))
    dataElement.select_by_visible_text(cource)

    dataElement = Select(browser.find_element_by_name('major.id'))
    dataElement.select_by_visible_text(major)
    #
    dataElement = browser.find_element_by_id('submitBtn')
    dataElement.click()

    mess = ''
    try:
        WebDriverWait(browser, 1).until(EC.alert_is_present())
        alert = browser.switch_to.alert
        mess += alert.text
        alert.accept()
    except TimeoutException:
        mess += browser.find_element_by_id('mess').text

    # sleep(1)
    browser.quit()
    return mess


def editUserAction(id, name, dob, address, email, cource, major):
    browser = webdriver.Chrome()
    browser.get("http://localhost:8080/user/edit/" + str(id))
    dataElement = browser.find_element_by_name('fullName')
    dataElement.clear()
    dataElement.send_keys(name)

    dataElement = browser.find_element_by_name('dateOfBirth')
    dataElement.send_keys(dob)

    dataElement = browser.find_element_by_name('address')
    dataElement.clear()
    dataElement.send_keys(address)

    dataElement = browser.find_element_by_name('email')
    dataElement.clear()
    dataElement.send_keys(email)

    dataElement = Select(browser.find_element_by_name('course.id'))
    dataElement.select_by_visible_text(cource)

    dataElement = Select(browser.find_element_by_name('major.id'))
    dataElement.select_by_visible_text(major)
    #
    dataElement = browser.find_element_by_id('submitBtn')
    dataElement.click()

    mess = ''
    try:
        WebDriverWait(browser, 1).until(EC.alert_is_present())
        alert = browser.switch_to.alert
        text_alert = alert.text
        if text_alert == 'Bạn có chắc muốn lưu':
            alert.accept()
            mess += browser.find_element_by_id('mess').text
        else:
            mess += text_alert
            alert.accept()

    except TimeoutException:
        pass

    # sleep(1)
    browser.quit()
    return mess


def editUserPageAction(id):
    browser = webdriver.Chrome()
    browser.get("http://localhost:8080/user/edit/" + str(id))
    dataElement = browser.find_element_by_name('fullName')
    fullName = dataElement.get_attribute('value')

    dataElement = browser.find_element_by_name('dateOfBirth')
    dateOfBirth = dataElement.get_attribute('value')
    dateOfBirth = '/'.join(dateOfBirth.split('-')[::-1])

    dataElement = browser.find_element_by_name('address')
    address = dataElement.get_attribute('value')

    dataElement = browser.find_element_by_name('email')
    email = dataElement.get_attribute('value')

    dataElement = Select(browser.find_element_by_name('course.id'))
    course = dataElement.first_selected_option.text

    dataElement = Select(browser.find_element_by_name('major.id'))
    major = dataElement.first_selected_option.text
    user_data = (fullName, dateOfBirth, address, email, course, major)
    browser.quit()
    return user_data


def deleteUserAction(id, accept=False):
    browser = webdriver.Chrome()
    browser.get("http://localhost:8080/listUser/")
    dataElement = browser.find_element_by_id('/user/delete/' + str(id))
    dataElement.click()
    check = False
    try:
        WebDriverWait(browser, 1).until(EC.alert_is_present())
        alert = browser.switch_to.alert
        if accept:
            alert.accept()
            user = getUserByIdForDel(id)
            if user.get('status') == 0 and (user.get('code') not in browser.page_source):
                check = True
        else:
            alert.dismiss()
            user = getUserByIdForDel(id)
            if user.get('status') == 1 and (user.get('code') in browser.page_source):
                check = True

    except TimeoutException:
        pass
    browser.quit()
    return check
