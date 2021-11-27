import {Button, Col, Form, Row} from "react-bootstrap";
import CustomNavbar from "../sub-components/navbar/CustomNavbar";
import {useState} from "react";
import {loginRequest} from "../../utils/requests";
import {setCookie} from "../../utils/cookies";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {setUserAction} from "../../store/actionCreators/actionCreators";
import {tokenToPayload} from "../../utils/token";
import {useHistory, useNavigate} from "react-router-dom";

const links = [
    {
        name: "Регистрация (Абитуриент)",
        href: "/student/register"
    },
    {
        name: "Регистрация (Приемная комиссия)",
        href: "/worker/register"
    }
]

const LoginForm = (props) => {
    const [loginInfo, setLoginInfo] = useState({
        email: "",
        password: ""
    });

    return (
        <Form className="mt-5">
            <Form.Control className="form-rounded mt-5" placeholder="Логин" onChange={e => {
                setLoginInfo({
                    ...loginInfo,
                    email: e.target.value
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

const Login = (props) => {

    const navigate = useNavigate()

    const processToken = (token) => {
        const payload = tokenToPayload(token)
        props.setUserDispatch(payload)
        setCookie('token', token)
        if (payload.role === 'EMPLOYEE') {
            navigate('/university/')
        } else if (payload.role === 'ENROLLEE') {
            navigate('/student/universities/')
        }
    }

    const submit = (loginInfo) => {
        loginRequest(loginInfo)
            .then(response => {
                if (response.status === 200) {
                    response.text()
                        .then(token => processToken(token))
                }
            })

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

const mapStateToProps = (state) => {
    return {
        user: state.user
    }
}

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators({
            setUserDispatch: setUserAction
        },
        dispatch)
}

export default connect(mapStateToProps, mapDispatchToProps)(Login)