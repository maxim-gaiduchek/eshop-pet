import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {getUser} from "../Services/UserService";

const defaultUser = {
    name: "",
    surname: "",
};

export function UserPage() {
    document.title = "User Account | E-Shop Pet";
    const {id} = useParams();
    const [user, setUser] = useState(defaultUser);
    useEffect(() => {
        getUser(id)
            .then(user => {
                setUser(user);
                document.title = `${user.name} ${user.surname} | E-Shop Pet`;
            })
            .catch(() => setUser(defaultUser))
    }, []);
    return (
        <p>{user.name} {user.surname}, id: {user.id}</p>
    )
}
