import {BrowserRouter, Route, Routes} from "react-router-dom";
import MainUniversity from "./components/pages/MainUniversity";
import MyUniversity from "./components/pages/MyUniversity";
import Login from "./components/pages/Login";
import StudentRegister from "./components/pages/StudentRegister";

function App() {
  return (
      <BrowserRouter>
        <div className="App">
            <Routes>
                <Route path="login" element={<Login/>}/>
                <Route path="university/my" element={<MyUniversity/>}/>
                <Route path="university/" element={<MainUniversity/>}/>
                <Route path="student/register" element={<StudentRegister/>}/>
            </Routes>
        </div>
      </BrowserRouter>
  );
}

export default App;
