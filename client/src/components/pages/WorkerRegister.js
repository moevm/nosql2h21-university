import {Button, Col, Form, Row} from "react-bootstrap";
import CustomNavbar from "../sub-components/navbar/CustomNavbar";
import {useState} from "react";

const RegistrationForm = () => {
    const [registerInfo, setRegisterInfo] = useState({
        firstName: "",
        lastName: "",
        patronymic: "",

        email: "",
        password: "",
        dob: null,

        university: ""
    })
    const [cities, setCities] = useState([])
    const [universities, setUniversities] = useState([])

    useState(() => {
        //TODO load cities
    }, [])

    const register = () => {
        console.log(registerInfo)
        //TODO sent register info
    }

    const loadUniversities = (city) => {

    }

    return (
        <div className="mt-5">
            <Row>
                <Col/>
                <Col>
                    <Form>
                        <Row>
                            <Col>
                                <Form.Control placeholder="Фамилия" className="mt-3" onChange={e => {
                                    setRegisterInfo({
                                        ...registerInfo,
                                        lastName: e.target.value
                                    })
                                }}/>
                                <Form.Control placeholder="Имя" className="mt-3" onChange={e => {
                                    setRegisterInfo({
                                        ...registerInfo,
                                        firstName: e.target.value
                                    })
                                }}/>
                                <Form.Control placeholder="Отчество" className="mt-3" onChange={e => {
                                    setRegisterInfo({
                                        ...registerInfo,
                                        patronymic: e.target.value
                                    })
                                }}/>
                            </Col>

                            <Col>
                                <Form.Control placeholder="Почта" className="mt-3" onChange={e => {
                                    setRegisterInfo({
                                        ...registerInfo,
                                        email: e.target.value
                                    })
                                }}/>
                                <Form.Control type="password" placeholder="Пароль" className="mt-3" onChange={e => {
                                    setRegisterInfo({
                                        ...registerInfo,
                                        password: e.target.value
                                    })
                                }}/>
                                <Form.Control type="date" placeholder="Дата рождения" className="mt-3" onChange={e => {
                                    setRegisterInfo({
                                        ...registerInfo,
                                        dob: e.target.value
                                    })
                                }}/>
                            </Col>
                        </Row>

                        <Row>
                            <Col>
                                <Form.Select placeholder="Университет" className="mt-3" onChange={e => {

                                }}>
                                    <option value={null}>Город...</option>
                                    {universities && universities.map(university => (
                                        <option value={university.id}>{university.name}</option>
                                    ))}
                                </Form.Select>
                            </Col>

                            <Col>
                                <Form.Select placeholder="Университет" className="mt-3" onChange={e => {
                                    setRegisterInfo({
                                        ...registerInfo,
                                        university: e.target.value
                                    })
                                }}>
                                    <option value={null}>Университет</option>
                                    {universities && universities.map(university => (
                                        <option value={university.id}>{university.name}</option>
                                    ))}
                                </Form.Select>
                            </Col>
                        </Row>

                        <Row className="justify-content-center mt-5">
                            <Button variant="primary" className="w-50" onClick={() => register()}>Зарегистрироваться</Button>
                        </Row>

                    </Form>
                </Col>
                <Col/>
            </Row>
        </div>
    )
}

const links = [
    {
        name: "Вход",
        href: "/login"
    }
]

const WorkerRegister = () => {
    return (
        <div>
            <CustomNavbar title="Регистрация сотрудника ВУЗа" links={links}/>
            <RegistrationForm/>
        </div>
    )
}

export default WorkerRegister;