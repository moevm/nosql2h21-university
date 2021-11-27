import {useState} from "react";
import {Button, Col, Container, Form, Row} from "react-bootstrap";
import '../../../css/form.css'

const StudentFilter = (props) => {
    const [filter, setFilter] = useState({
        lastName: "",
        firstName: "",
        patronymic: "",
        sumOfEgeResultsFrom: 0,

        city: "",
        isStatementExists: false,

        agreement: false,
        formOfEducation: "",
        directionOfStudy: ""
    })
    const [user, setUser] = useState({

    })

    return (
        <Container>
            <Row>
                <Col className="col-5">
                    <Form>
                        <Form.Group as={Row} className="mb-3" controlId="formPlaintextEmail">
                            <Form.Label column sm="2">
                                Фамилия
                            </Form.Label>
                            <Col sm="10">
                                <Form.Control className="form-rounded" placeholder="Фамилия" onChange={e => {
                                    setFilter({
                                        ...filter,
                                        lastName: e.target.value
                                    })
                                }}/>
                            </Col>
                        </Form.Group>

                        <Form.Group as={Row} className="mb-3" controlId="formPlaintextPassword">
                            <Form.Label column sm="2">
                                Имя
                            </Form.Label>
                            <Col sm="10">
                                <Form.Control className="form-rounded" placeholder="Имя" onChange={e => {
                                    setFilter({
                                        ...filter,
                                        firstName: e.target.value
                                    })
                                }}/>
                            </Col>
                        </Form.Group>

                        <Form.Group as={Row} className="mb-3" controlId="formPlaintextEmail">
                            <Form.Label column sm="2">
                                Отчество
                            </Form.Label>
                            <Col sm="10">
                                <Form.Control className="form-rounded" placeholder="Отчество" onChange={e => {
                                    setFilter({
                                        ...filter,
                                        patronymic: e.target.value
                                    })
                                }}/>
                            </Col>
                        </Form.Group>
                    </Form>
                </Col>
                <Col></Col>
                <Col className="col-6">
                    <Form>
                        {!props.forCurrentUniversity &&
                        <>
                            <Form.Group as={Row} className="mb-3" controlId="formPlaintextEmail">
                                <Form.Label column sm="3">
                                    Подал заявление
                                </Form.Label>
                                <Col sm="9">
                                    <Form.Select  className="form-rounded" onChange={e => {
                                        setFilter({
                                            ...filter,
                                            isStatementExists: e.target.value
                                        })
                                    }}>
                                        <option value={false}>Нет</option>
                                        <option value={true}>Да</option>
                                    </Form.Select>
                                </Col>
                            </Form.Group>

                            <Form.Group as={Row} className="mb-3" controlId="formPlaintextPassword">
                                <Form.Label column sm="3">
                                    Город
                                </Form.Label>
                                <Col sm="9">
                                    <Form.Control className="form-rounded" placeholder="Город" onChange={e => {
                                        setFilter({
                                            ...filter,
                                            city: e.target.value
                                        })
                                    }}/>
                                </Col>
                            </Form.Group>
                        </>
                        }

                        { props.forCurrentUniversity &&
                            <>
                                <Form.Group as={Row} className="mb-3" controlId="formPlaintextEmail">
                                    <Form.Label column sm="3">
                                        Согласие на зачисление
                                    </Form.Label>
                                    <Col sm="9">
                                        <Form.Select className="form-rounded" onChange={e => {
                                            setFilter({
                                                ...filter,
                                                agreement: e.target.value
                                            })
                                        }}>
                                            <option value={false}>Нет</option>
                                            <option value={true}>Да</option>
                                        </Form.Select>
                                    </Col>
                                </Form.Group>

                                <Form.Group as={Row} className="mb-3" controlId="formPlaintextEmail">
                                    <Form.Label column sm="3">
                                        Форма обучение
                                    </Form.Label>
                                    <Col sm="9">
                                        <Form.Select className="form-rounded" onChange={e => {
                                            setFilter({
                                                ...filter,
                                                formOfEducation: e.target.value
                                            })
                                        }}>
                                            <option value={"contract"}>Контракт</option>
                                            <option value={"budget"}>Бюджет</option>
                                        </Form.Select>
                                    </Col>
                                </Form.Group>

                                <Form.Group as={Row} className="mb-3" controlId="formPlaintextPassword">
                                    <Form.Label column sm="3">
                                        Направление подготовки
                                    </Form.Label>
                                    <Col sm="9">
                                        <Form.Control className="form-rounded" placeholder="Напправление подготовки" onChange={e => {
                                            setFilter({
                                                ...filter,
                                                directionOfStudy: e.target.value
                                            })
                                        }}/>
                                    </Col>
                                </Form.Group>
                            </>
                        }



                        <Form.Group as={Row} className="mb-3" controlId="formPlaintextEmail">
                            <Form.Label column sm="3">
                                Сумма баллов от
                            </Form.Label>
                            <Col sm="9">
                                <Form.Control className="form-rounded" type="number" defaultValue={0} onChange={e => {
                                    let value = parseInt(e.target.value)
                                    if (isNaN(value)) {
                                        value = 0
                                    }
                                    setFilter({
                                        ...filter,
                                        sumOfEgeResultsFrom: value
                                    })
                                }}/>
                            </Col>
                        </Form.Group>
                    </Form>
                </Col>
            </Row>
            <Row>
                <Col className="text-center">
                    <Button className="form-rounded" variant="secondary" onClick={() => props.submit(filter)}>Поиск</Button>
                </Col>
            </Row>
        </Container>

    )
}

export default StudentFilter