import {Button, Col, Form, Row} from "react-bootstrap";
import CustomNavbar from "../sub-components/navbar/CustomNavbar";
import {useEffect, useState} from "react";
import UniversityList from "../sub-components/list/UniversityList";
import {filterUniversitiesRequest, getUniversitiesRequest} from "../../utils/requests";


const UniversitiesForm = (props) => {
    const [filter, setFilter] = useState({
        name: "",
        city: "",
        direction: ""
    })

    return (
        <Form>
            <Row>
                <Col className="text-center">
                    <Form.Label>Название</Form.Label>
                    <Form.Control className="form-rounded" placeholder="Название" onChange={e => {
                        setFilter({
                            ...filter,
                            name: e.target.value
                        })
                    }}/>
                </Col>
                <Col className="text-center">
                    <Form.Label>Город</Form.Label>
                    <Form.Control className="form-rounded" placeholder="Город" onChange={e => {
                        setFilter({
                            ...filter,
                            city: e.target.value
                        })
                    }}/>
                </Col>
                <Col className="text-center">
                    <Form.Label>Направление подготовки</Form.Label>
                    <Form.Control className="form-rounded" placeholder="Направление" onChange={e => {
                        setFilter({
                            ...filter,
                            direction: e.target.value
                        })
                    }}/>
                </Col>
            </Row>
            <Row className="mt-3">
                <Col className="text-center">
                    <Button className="form-rounded" variant="secondary" onClick={() => props.submit(filter)}>Поиск</Button>
                </Col>
            </Row>
        </Form>
    )
}

const links = [
    {
        name: "Профиль",
        href: "#"
    }
]

const StudentUniversities = () => {
    const [universities, setUniversities] = useState([])

    useEffect(() => {
        getUniversitiesRequest().then(response => {
            if (response.status === 200) {
                response.json()
                    .then(res => setUniversities(res))
            }
        })
    }, [])

    const filterUniversity = (filter) => {
        filterUniversitiesRequest(filter)
            .then(response => {
                if (response.status === 200) {
                    response.json()
                        .then(res => setUniversities(res))
                }
            })
    }

    return (
        <div>
            <CustomNavbar title="ВУЗы" links={links}/>
            <Row className="mt-5">
                <Col xs={2}/>
                <Col>
                    <UniversitiesForm submit={(filter) => filterUniversity(filter)}/>
                    <UniversityList variant='view' universities={universities}/>
                </Col>
                <Col xs={2}/>
            </Row>
        </div>
    )
}

export default StudentUniversities