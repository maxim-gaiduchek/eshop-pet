import {useEffect, useState} from "react";
import {login} from "../../Services/AuthService";
import {Link, useNavigate} from "react-router-dom";
import {Input} from "antd";
import {MainLayout} from "../../Components/Layouts/MainLayout";
import {CenteredLayout} from "../../Components/Layouts/CenteredLayout";
import {SubmitButton} from "../../Components/Buttons/SubmitButton";

export function LoginPage() {
    document.title = "Login | E-Shop Pet";
    const navigate = useNavigate();
    const [userLogin, setUserLogin] = useState("");
    const [userPassword, setUserPassword] = useState("");
    const [disabled, setDisabled] = useState(true);
    useEffect(() => {
        let loginUserId = localStorage.getItem("loginUserId");
        if (loginUserId) {
            navigate("/shop");
        }
    }, []);
    const loginOnClick = async (e) => {
        e.preventDefault();
        setDisabled(true);
        login(userLogin, userPassword)
            .then(() => {
                navigate("/shop");
                // setDisabled(false);
            })
            .catch(() => {
                setDisabled(false);
            });
    }
    const setupData = (value, setter) => {
        setDisabled(!userLogin || !userPassword);
        setter(value);
    }
    return (
        <MainLayout>
            <CenteredLayout>
                <form onSubmit={(e) => loginOnClick(e)} style={{textAlign: "center"}}>
                    <h1>Login</h1>
                    <p>Don't have an account? <Link to={"/register"}>Register</Link></p>
                    <Input type={"email"} placeholder={"Email"}
                           value={userLogin}
                           onChange={(e) => setupData(e.target.value, setUserLogin)}
                           style={{width: "100%", margin: "10px 10px"}}/>
                    <Input type={"password"} placeholder={"Password"}
                           value={userPassword}
                           onChange={(e) => setupData(e.target.value, setUserPassword)}
                           style={{width: "100%", margin: "10px 10px"}}/>
                    <SubmitButton disabled={disabled} value={"Login"}/>
                </form>
            </CenteredLayout>
        </MainLayout>
    )
}
