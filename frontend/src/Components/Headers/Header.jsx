import {Link} from "react-router-dom";

export function Header() {
    return (
        <div>
            <h1>E-Shop Pet</h1>
            <Link to={"/login"}>
                <button>Login</button>
            </Link>
        </div>
    )
}
