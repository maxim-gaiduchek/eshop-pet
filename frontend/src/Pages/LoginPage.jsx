import {useState} from "react";
import {login} from "../Services/LoginService";
import {useNavigate} from "react-router-dom";
import {CenteredLayout} from "../Components/Layouts/CenteredLayout";
import {Button, Flex, Input} from "antd";

export function LoginPage() {
    document.title = "Login | E-Shop Pet";
    const [userLogin, setUserLogin] = useState("");
    const [userPassword, setUserPassword] = useState("");
    const navigate = useNavigate();
    const loginOnClick = async () => {
        const user = await login(userLogin, userPassword);
        localStorage.setItem("loginUserId", user.id);
        navigate("/shop");
    }
    return (
        <CenteredLayout>
            <Flex style={{flexDirection: "column", alignItems: "center"}}>
                <h1>Login</h1>
                <Input type={"text"} placeholder={"Login"}
                       value={userLogin}
                       onChange={(e) => setUserLogin(e.target.value)}
                       style={{width: "100%"}}/>
                <Input type={"password"} placeholder={"Password"}
                       value={userPassword}
                       onChange={(e) => setUserPassword(e.target.value)}
                       style={{width: "100%"}}/>
                <Button onClick={loginOnClick}>Login</Button>
            </Flex>
        </CenteredLayout>
    )
}
