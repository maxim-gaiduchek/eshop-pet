import {useState} from "react";
import {login} from "../Services/LoginService";
import {Link, useNavigate} from "react-router-dom";
import {Button, Input} from "antd";
import {MainLayout} from "../Components/Layouts/MainLayout";
import {CenteredLayout} from "../Components/Layouts/CenteredLayout";

export function LoginPage() {
    document.title = "Login | E-Shop Pet";
    const [userLogin, setUserLogin] = useState("");
    const [userPassword, setUserPassword] = useState("");
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();
    const loginOnClick = async () => {
        setLoading(true);
        login(userLogin, userPassword)
            .then(user => {
                console.log(user);
                localStorage.setItem("loginUserId", user.id);
                navigate("/shop");
            })
            .catch(() => {
                setLoading(false);
            });
    }
    return (
        <MainLayout>
            <CenteredLayout>
                <h1>Login</h1>
                <p>Don't have an account? <Link to={"/register"}>Register</Link></p>
                <Input type={"email"} placeholder={"Email"}
                       value={userLogin}
                       onChange={(e) => setUserLogin(e.target.value)}
                       style={{width: "100%"}}/>
                <Input type={"password"} placeholder={"Password"}
                       value={userPassword}
                       onChange={(e) => setUserPassword(e.target.value)}
                       style={{width: "100%"}}/>
                <Button onClick={loginOnClick} disabled={loading} loading={loading}>Login</Button>
            </CenteredLayout>
        </MainLayout>
    )
}
