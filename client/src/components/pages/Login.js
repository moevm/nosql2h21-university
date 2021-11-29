import {Button, Col, Form, Row, Toast} from "react-bootstrap";
import CustomNavbar from "../sub-components/navbar/CustomNavbar";
import {useState} from "react";
import {loginRequest} from "../../utils/requests";
import {deleteCookie, setCookie} from "../../utils/cookies";
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
    // {
    //     name: "Регистрация (Приемная комиссия)",
    //     href: "/worker/register"
    // }
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
            <Button variant="outline-success" id="login-button" className="form-rounded mt-5"
                    onClick={() => props.submit(loginInfo)}>Войти</Button>
        </Form>
    )
}

const Login = (props) => {
    const [showAlert, setShowAlert] = useState(false)

    const navigate = useNavigate()

    useState(() => {
        deleteCookie('token')
    }, [])

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
        setShowAlert(false)
        loginRequest(loginInfo)
            .then(response => {
                if (response.status === 200) {
                    response.text()
                        .then(token => processToken(token))
                } else if (response.status === 404) {
                    setShowAlert(true)
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
                <Col>
                    <Toast bg={"warning"} show={showAlert} onClose={() => {
                        setShowAlert(false)
                    }}>
                        <Toast.Header>
                            <img
                                src="holder.js/20x20?text=%20"
                                className="rounded me-2"
                                alt=""
                            />
                            <strong className="me-auto">Ошибка</strong>
                            <small>11 mins ago</small>
                        </Toast.Header>
                        <Toast.Body>Неверные логин/пароль</Toast.Body>
                    </Toast>
                </Col>
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