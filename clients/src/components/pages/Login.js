import {Button, Col, Form, Row} from "react-bootstrap";
import CustomNavbar from "../sub-components/navbar/CustomNavbar";
import {useState} from "react";

const links = [
    {
        name: "Регистрация (Абитуриент)",
        href: "/student/register"
    },
    {
        name: "Регистрация (Приемная комиссия)",
        href: "#"
    }
]

const LoginForm = (props) => {
    const [loginInfo, setLoginInfo] = useState({
        login: "",
        password: ""
    });

    return (
        <Form className="mt-5">
            <Form.Control className="form-rounded mt-5" placeholder="Логин" onChange={e => {
                setLoginInfo({
                    ...loginInfo,
                    login: e.target.value
                })
            }}/>
            <Form.Control type="password" className="form-rounded mt-3" placeholder="Пароль" onChange={e => {
                setLoginInfo({
                    ...loginInfo,
                    password: e.target.value
                })
            }}/>
            <Button variant="outline-success" id="login-button" className="form-rounded mt-5" onClick={() => props.submit(loginInfo)}>Войти</Button>
        </Form>
    )
}

const Login = () => {

    const submit = (loginInfo) => {
        //TODO
        console.log(loginInfo)
    }

    return (
        <>
            <CustomNavbar title="Вход" links={links}/>
            <Row className="mt-5">
                <Col></Col>
                <Col>
                    <LoginForm submit={submit}/>
                </Col>
                <Col></Col>
            </Row>
        </>
    )
}

export default Login