import {useEffect, useState} from "react";
import {getUser} from "../../../Services/UserService";
import {Flex} from "antd";
import {Link} from "react-router-dom";

export function LoginUserProfile() {
    const [user, setUser] = useState({name: "Maksym", surname: "Gaiduchek"});
    let loginUserId = localStorage.getItem("loginUserId");
    useEffect(() => {
        getUser(loginUserId)
            .then(data => data.json())
            .then(json => setUser(json))
    }, []);
    return (
        <Flex>
            <Link to={"/users/" + user.id} style={{textDecoration: "underline"}}>
                {user.name} {user.surname}
            </Link>
        </Flex>
    )
}
