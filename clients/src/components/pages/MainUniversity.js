import CustomNavbar from "../sub-components/navbar/CustomNavbar";
import StudentFilter from "../sub-components/filter/StudentFilter";
import {Col, Row, Table} from "react-bootstrap";
import {useEffect, useState} from "react";
import {getAllStudentsRequest, getStudentsByFilterRequest} from "../../utils/requests";


const StudentTable = (props) => {
    return (
        <Table striped bordered hover>
            <thead>
            <tr>
                <th>№</th>
                <th>ФИО</th>
                <th>Дата рождения</th>
                <th>Баллы ЕГЭ</th>
                <th>Индив. достиж-я</th>
                <th>Город</th>
                <th>Учебное заведение</th>
                <th>Подал заявление</th>
            </tr>
            </thead>
            <tbody>
            {
                props.students && props.students.map((student, index) => (
                    <tr key={student.id}>
                        <td>{index + 1}</td>
                        <td>{`${student.lastName} ${student.firstName} ${student.patronymic}`}</td>
                        <td>{new Date(student.dob).toLocaleDateString()}</td>
                        <td>
                            {student.egeResults && student.egeResults.map(
                                result => (<span key={result.subject}>{result.subject + ": " + result.score + " "}<br/></span>))}
                        </td>
                        <td>{student.individualAchievements && student.individualAchievements.map(
                            achievement => (<span key={achievement}>{achievement + " "}<br/></span>))}
                        </td>
                        <td>{student.city}</td>
                        <td>{student.school}</td>
                        <td>
                            {student.isStatementExists ? 'ДА' : 'НЕТ'}
                        </td>
                    </tr>
                ))
            }
            </tbody>
        </Table>
    )
}

const links = [
    {
        name: "Статистика",
        href: "#"
    },
    {
        name: "Абитуриенты, подавшие заявление в ваш вуз",
        href: "/university/my"
    },
    {
        name: "Настройки",
        href: "/employee/settings"
    },
]

const MainUniversity = () => {
    const [students, setStudents] = useState([])

    useEffect(() => {
        getAllStudentsRequest()
            .then(response => response.json())
            .then(students => setStudents(students))
    }, [])

    const updateTableForFilter = filter => {
        getStudentsByFilterRequest(filter)
            .then(response => {
            if (response.status === 200) {
                return response.json()
            }
            return students
            })
            .then(students => {
                setStudents(students)
            })
    }

    return (
        <div>
            <CustomNavbar title="Поиск всех абитуриентов" links={links}/>
            <div className="mt-3">
                <StudentFilter  submit={updateTableForFilter} />
            </div>
            <Row className="mt-5">
                <Col></Col>
                <Col className="col-8">
                    <StudentTable students={students}/>
                </Col>
                <Col></Col>
            </Row>
        </div>
    )
}

export default MainUniversity