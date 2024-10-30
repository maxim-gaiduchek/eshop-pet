import {useEffect, useState} from "react";
import {getUser} from "../../../Services/UserService";
import {Button, Flex, Tooltip} from "antd";
import {Link, useNavigate} from "react-router-dom";
import {LogoutOutlined, UserOutlined} from "@ant-design/icons";
import {LoginRegisterButtons} from "./LoginRegisterButtons";
import {logout} from "../../../Services/AuthService";

const defaultUser = {
    name: "",
    surname: "",
};

export function LoginUserProfile() {
    const [user, setUser] = useState(defaultUser);
    const loginUserId = localStorage.getItem("loginUserId");
    const navigate = useNavigate();
    useEffect(() => {
        if (!loginUserId) {
            return;
        }
        getUser(loginUserId)
            .then(user => setUser(user))
            .catch(() => setUser(defaultUser))
    }, []);
    const logoutOnClick = async () => {
        logout()
            .then(() => {
                setUser(defaultUser);
                navigate("/shop");
            })
    }
    return (user.id ?
            <>
                <Flex style={{
                    alignItems: "center",
                }}>
                    <Button type={"text"} onClick={logoutOnClick} style={{
                        margin: "0 5px"
                    }}>
                        <Tooltip title={"Logout"} placement={"bottom"}>
                            <LogoutOutlined style={{
                                transform: "rotate(180deg)"
                            }}/>
                        </Tooltip>
                    </Button>
                    <Link to={"/users/" + user.id} style={{
                        textDecoration: "none",
                        color: "black"
                    }}>

                        <Tooltip title={"Account"} placement={"bottom"}>
                            <Button type={"text"} onClick={logoutOnClick} style={{
                                margin: "0 5px",
                            }}>
                                <UserOutlined style={{marginRight: "10px"}}/>
                                <p>{user.name} {user.surname}</p>
                            </Button>
                        </Tooltip>
                    </Link>
                </Flex>
            </> :
            <>
                <LoginRegisterButtons/>
            </>
    )
}
