import CustomNavbar from "../sub-components/navbar/CustomNavbar";
import {Button, Col, Form, Image, InputGroup, Row} from "react-bootstrap";
import {useState} from "react";
import "../../css/image.css"

const links = [
    {
        name: "Вход",
        href: "/login"
    }
]

const defaultImageSrc = "/images/defaultAvatar.png"

const RegistrationForm = () => {
    const [registerInfo, setRegisterInfo] = useState({
        image: defaultImageSrc,

        firstName: "",
        lastName: "",
        patronymic: "",
        password: "",

        email: "",
        dob: null,
        school: "",
        city: "",

        egeResults: [],
        individualAchievements: []
    })

    const onImageUpload = (imageFile) => {
        console.log(imageFile)//TODO: sent file to server -> get name ->  get url -> update register info
    }

    const addEgeResult = () => {
        setRegisterInfo({
            ...registerInfo,
            egeResults: [...registerInfo.egeResults, {subject: "", result: 0}]
        })
    }

    const deleteEgeResult = (index) => {
        const newRegisterInfo = {
            ...registerInfo
        }

        newRegisterInfo.egeResults.splice(index, 1)
        setRegisterInfo(newRegisterInfo)
    }

    const updateEgeSubject = (index, value) => {
        const newRegisterInfo = {
            ...registerInfo
        }

        newRegisterInfo.egeResults[index].subject = value
        setRegisterInfo(newRegisterInfo)
    }

    const updateEgeScore = (index, value) => {
        const newRegisterInfo = {
            ...registerInfo
        }

        newRegisterInfo.egeResults[index].result = value
        setRegisterInfo(newRegisterInfo)
    }

    const register = () => {
        console.log(registerInfo)
        //TODO sent register info
    }

    return (
        <Form>
            <Row>
                <Col></Col>
                <Col>
                    <Image src={registerInfo.image} roundedCircle className="avatar-register"/>
                    <Form.Group controlId="formFile" className="mb-3">
                        <Form.Control type="file" accept=".png, .jpg, .jpeg" onLoad={event => console.log(event)} onChange={(e) => onImageUpload(e.target.files[0])}/>
                    </Form.Group>
                </Col>
                <Col>
                    <Form.Control className="mt-3" placeholder="Имя" onChange={e => {
                        setRegisterInfo({
                            ...registerInfo,
                            firstName: e.target.value
                        })
                    }}/>
                    <Form.Control className="mt-3" placeholder="Фамилия" onChange={e => {
                        setRegisterInfo({
                            ...registerInfo,
                            lastName: e.target.value
                        })
                    }}/>
                    <Form.Control className="mt-3" placeholder="Отчество" onChange={e => {
                        setRegisterInfo({
                            ...registerInfo,
                            patronymic: e.target.value
                        })
                    }}/>
                    <Form.Control className="mt-3" placeholder="Пароль" type="password" onChange={e => {
                        setRegisterInfo({
                            ...registerInfo,
                            password: e.target.value
                        })
                    }}/>
                </Col>
                <Col></Col>
            </Row>
            <Row>
                <Col className="col-3"></Col>
                <Col className="col-6">
                    <Row className="mt-5">
                        <Col>
                            <Form.Control placeholder="Почта" onChange={e => {
                                setRegisterInfo({
                                    ...registerInfo,
                                    email: e.target.value
                                })
                            }}/>
                        </Col>
                        <Col>
                            <Form.Control type="date" placeholder="Дата рождения" onChange={e => {
                                setRegisterInfo({
                                    ...registerInfo,
                                    dob: e.target.value
                                })
                            }}/>
                        </Col>
                    </Row>
                    <Form.Control placeholder="Учебное заведение" className="mt-3" onChange={e => {
                        setRegisterInfo({
                            ...registerInfo,
                            school: e.target.value
                        })
                    }}/>
                    <Form.Control placeholder="Город" className="mt-3" onChange={e => {
                        setRegisterInfo({
                            ...registerInfo,
                            city: e.target.value
                        })
                    }}/>
                    <Row className="mt-3">
                        <Col>
                            <InputGroup className="mb-3">
                                <InputGroup.Text id="basic-addon1">ЕГЭ</InputGroup.Text>
                                <Button variant="success" onClick={() => addEgeResult()}>+</Button>
                            </InputGroup>
                            {registerInfo.egeResults && registerInfo.egeResults.map((egeRes, index) => (
                                <InputGroup className="mb-3" key={index}>
                                    <Form.Control placeholder="Предмет" onChange={e => updateEgeSubject(index, e.target.value)}/>
                                    <Form.Control type="number" placeholder="Баллы" onChange={e => updateEgeScore(index, e.target.value)}/>
                                    <Button variant="danger" onClick={() => deleteEgeResult(index)}>-</Button>
                                </InputGroup>
                            ))}
                        </Col>
                        <Col>
                            <InputGroup className="mb-3">
                                <InputGroup.Text id="basic-addon1">Индивидуальные достижения</InputGroup.Text>
                                <Button variant="success" onClick={() => {
                                    setRegisterInfo({
                                        ...registerInfo,
                                        individualAchievements: [...registerInfo.individualAchievements, ""]
                                    })
                                }}>+</Button>
                            </InputGroup>
                            {registerInfo.individualAchievements && registerInfo.individualAchievements.map((achievement, index) => (
                                <InputGroup className="mb-3" key={index}>
                                    <Form.Control placeholder="Достижение" onChange={e => {
                                        const newRegisterInfo = {
                                            ...registerInfo
                                        }
                                        newRegisterInfo.individualAchievements[index] = e.target.value
                                        setRegisterInfo(newRegisterInfo)
                                    }}/>
                                    <Button variant="danger" onClick={() => {
                                        const newRegisterInfo = {
                                            ...registerInfo
                                        }

                                        newRegisterInfo.individualAchievements.splice(index, 1)
                                        setRegisterInfo(newRegisterInfo)
                                    }}>-</Button>
                                </InputGroup>
                            ))}
                        </Col>
                    </Row>
                    <Row className="justify-content-center mt-5"><Button variant="primary" className="w-50">Зарегистрироваться</Button></Row>

                </Col>
                <Col className="col-3"></Col>
            </Row>
        </Form>
    )
}

const StudentRegister = () => {
    return (
        <>
            <CustomNavbar title="Регистрация абитуриента" links={links}/>
            <RegistrationForm/>
        </>
    )
}

export default StudentRegister