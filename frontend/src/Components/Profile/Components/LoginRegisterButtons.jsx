import {Link} from "react-router-dom";
import {Button, Flex} from "antd";

export function LoginRegisterButtons() {
    return (
        <Flex>
            <Link to={"/login"}>
                <Button>Login</Button>
            </Link>
            <Link to={"/register"}>
                <Button>Register</Button>
            </Link>
        </Flex>
    )
}
