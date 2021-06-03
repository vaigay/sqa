import mysql.connector
from mysql.connector import Error
from time import sleep

cnx = mysql.connector.connect(host='localhost', database='sqa', user='root', password='database!')


def getLastUser():
    cursor = cnx.cursor()

    query = "SELECT u.full_name, u.date_of_birth, u.address, u.email, c.name, m.name_major \
            FROM user AS u, major AS m, course AS c \
            WHERE u.course_id = c.id \
            AND u.major_id = m.id \
            ORDER BY u.id DESC LIMIT 1;"

    cursor.execute(query)
    user = cursor.fetchone()
    user = list(user)
    user[1] = user[1].strftime('%m/%d/%Y')
    return tuple(user)


def getIdLastUser():
    cursor = cnx.cursor()

    query = "SELECT user.id \
                FROM user \
                ORDER BY user.id DESC LIMIT 1;"

    cursor.execute(query)
    user = cursor.fetchone()
    return user[0]


def getUserById(idUser):
    cursor = cnx.cursor()
    query = "SELECT u.full_name, u.date_of_birth, u.address, u.email, c.name, m.name_major \
            FROM user AS u, major AS m, course AS c \
            WHERE u.course_id = c.id \
            AND u.major_id = m.id \
            AND u.id = %s;"
    condition = (idUser,)
    cursor.execute(query, condition)
    user = cursor.fetchone()
    if user:
        user = list(user)
        user[1] = user[1].strftime('%m/%d/%Y')
    else:
        user = []
    return tuple(user)


def getUserByIdForDel(idUser):
    cursor = cnx.cursor()
    query = "SELECT user.code, user.status \
            FROM user \
            WHERE user.id = %s"
    condition = (idUser,)
    cursor.execute(query, condition)
    data = cursor.fetchone()
    user = {
        'code': data[0],
        'status': data[1]
    }
    return user


def deleteLastUser():
    cursor = cnx.cursor()
    query = "DELETE FROM user ORDER BY id DESC LIMIT 1"
    cursor.execute(query)
    cnx.commit()
    return True
