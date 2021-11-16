import CustomNavbar from "../sub-components/navbar/CustomNavbar";
import StudentFilter from "../sub-components/filter/StudentFilter";
import {Col, Row, Table} from "react-bootstrap";
import {useEffect, useState} from "react";
import {getStudentsForUniversityByFilterRequest, getStudentsForUniversityRequest} from "../../utils/requests";


const StudentTable = (props) => {
    return (
        <Table striped bordered hover>
            <thead>
            <tr>
                <th>№</th>
                <th>ФИО</th>
                <th>Баллы ЕГЭ</th>
                <th>Индив. достиж-я</th>
                <th>Согласие на зачисление</th>
                <th>Форма обучения</th>
                <th>Направление подготовки</th>
            </tr>
            </thead>
            <tbody>
            {
                props.students && props.students.map((student, index) => (
                    <tr key={student.id}>
                        <td>{index + 1}</td>
                        <td>{`${student.lastName} ${student.firstName} ${student.patronymic}`}</td>
                        <td>
                            {student.egeResults && student.egeResults.map(
                                result => (<span key={result.subject}>{result.subject + ": " + result.score + " "}<br/></span>))}
                        </td>
                        <td>
                            {student.individualAchievements && student.individualAchievements.map(
                                achievement => (<span key={achievement}>{achievement + " "}<br/></span>))}
                        </td>
                        <td>
                            {student.agreement && 'ДА'}
                            {!student.agreement && 'НЕТ'}
                        </td>
                        <td>{student.formOfEducation}</td>
                        <td>{student.directionOfStudy}</td>
                    </tr>
                ))
            }
            </tbody>
        </Table>
    )
}

const links = [
    {
        name: "Главная",
        href: "/university"
    }
]

const MyUniversity = () => {
    const [students, setStudents] = useState([])

    //todo: change mock to getting id from cookies
    const universityId = '61897b373f19466edadd7324';

    useEffect(() => {
        getStudentsForUniversityRequest(universityId)
            .then(response => response.json())
            .then(students => setStudents(students))
    }, [])

    const updateTableForFilter = filter => {
        getStudentsForUniversityByFilterRequest(universityId, filter)
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
            <CustomNavbar title="Заявления" links={links}/>
            <div className="mt-3">
                <StudentFilter  submit={updateTableForFilter} forCurrentUniversity={true}/>
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

export default MyUniversity