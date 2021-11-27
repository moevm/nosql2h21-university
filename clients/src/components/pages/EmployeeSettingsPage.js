import CustomNavbar from "../sub-components/navbar/CustomNavbar";
import {Button, Col, Form, Row} from "react-bootstrap";
import {useRef} from "react";
import {importFileRequest} from "../../utils/requests";

const links = [
    {
        name: "Главная",
        href: "/university"
    }
]

const EmployeeSettingsPage = () => {
    const inputRef = useRef(null);

    return (
        <div>
            <CustomNavbar title="Настройки" links={links}/>
            <Row className="mt-5">
                <Col xs={2}/>
                <Col className="text-center">
                    <Button variant={"secondary"} className={"form-rounded"} href="http://localhost:8080/api/employees/export" target="_blank">Экспорт данных</Button>
                </Col>
                <Col className={"text-center"}>
                    <Form.Control className="d-none" type="file" ref={inputRef}  onChange={(e) => {
                        importFileRequest(e.target.files[0])
                            .then(response => {
                                console.log(response)
                            })
                    }}/>
                    <Button variant={"secondary"} className={"form-rounded"} onClick={() => {
                        inputRef.current?.click()
                    }}>Импорт данных</Button>
                </Col>
                <Col xs={2}/>
            </Row>
        </div>
    )
}

export default EmployeeSettingsPage