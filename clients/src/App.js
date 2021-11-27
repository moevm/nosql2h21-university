import {BrowserRouter, Route, Routes} from "react-router-dom";
import MainUniversity from "./components/pages/MainUniversity";
import MyUniversity from "./components/pages/MyUniversity";
import Login from "./components/pages/Login";
import StudentRegister from "./components/pages/StudentRegister";
import WorkerRegister from "./components/pages/WorkerRegister";
import {bindActionCreators} from "redux";
import {setUserAction} from "./store/actionCreators/actionCreators";
import {useEffect} from "react";
import {getCookie} from "./utils/cookies";
import {tokenToPayload} from "./utils/token";
import {connect} from "react-redux";
import StudentUniversities from "./components/pages/StudentUniversities";
import EmployeeSettingsPage from "./components/pages/EmployeeSettingsPage";

function App(props) {

    useEffect(() => {
        const token = getCookie('token')
        if (token) {
            const payload = tokenToPayload(token)
            props.setUserDispatch(payload)
        }
    }, [])

  return (
      <BrowserRouter>
        <div className="App">
            <Routes>
                <Route path="login" element={<Login/>}/>
                <Route path="university/my" element={<MyUniversity/>}/>
                <Route path="university/" element={<MainUniversity/>}/>
                <Route path="student/register" element={<StudentRegister/>}/>
                <Route path="student/universities" element={<StudentUniversities/>}/>
                <Route path="worker/register" element={<WorkerRegister/>}/>
                <Route path="employee/settings" element={<EmployeeSettingsPage/>}/>
            </Routes>
        </div>
      </BrowserRouter>
  );
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

export default connect(mapStateToProps, mapDispatchToProps)(App);
