import {useEffect, useState} from "react";
import {getUser} from "../../../Services/UserService";
import {Flex} from "antd";
import {Link} from "react-router-dom";

const defaultUser = {
    name: "Test",
    surname: "Test",
};

export function LoginUserProfile() {
    const [user, setUser] = useState(defaultUser);
    let loginUserId = localStorage.getItem("loginUserId");
    useEffect(() => {
        getUser(loginUserId)
            .then(data => data.json())
            .then(json => setUser(json))
            .catch(() => setUser(defaultUser))
    }, []);
    return (
        <Flex>
            <Link to={"/users/" + user.id} style={{textDecoration: "underline"}}>
                {user.name} {user.surname}
            </Link>
        </Flex>
    )
}
