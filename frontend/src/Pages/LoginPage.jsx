import {useState} from "react";
import {login} from "../Services/LoginService";

export function LoginPage() {
    const [userLogin, setUserLogin] = useState("")
    const [userPassword, setUserPassword] = useState("")
    return (
        <div>
            <h2>Login</h2>
            <input type={"text"} placeholder={"Login"}
                   value={userLogin}
                   onChange={(e) => setUserLogin(e.target.value)}/><br/>
            <input type={"password"} placeholder={"Password"}
                   value={userPassword}
                   onChange={(e) => setUserPassword(e.target.value)}/><br/>
            <button onClick={() => {
                let user = login(userLogin, userPassword)
                localStorage.setItem("loginUserId", user.id)
            }}>Login</button>
        </div>
    )
}
