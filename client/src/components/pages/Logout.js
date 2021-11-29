import {useNavigate} from "react-router-dom";
import {useState} from "react";
import {deleteCookie} from "../../utils/cookies";

const Logout = () => {
    const navigate = useNavigate()

    useState(() => {
        deleteCookie('token')
        console.log(123)
        navigate('/login')
    }, [])

    return (
        <div>

        </div>
    )
}

export default Logout