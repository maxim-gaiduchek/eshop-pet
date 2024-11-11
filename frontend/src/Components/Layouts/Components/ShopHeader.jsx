import {Flex} from "antd";
import {LoginProfile} from "../../Profile/LoginProfile";
import {Header} from "antd/lib/layout/layout";
import {Link} from "react-router-dom"
import {secondaryBackgroundColor} from "../../../colors";

export function ShopHeader({headerHeight}) {
    return (
        <Header style={{
            width: "100%",
            height: headerHeight ? headerHeight : 100,
            backgroundColor: secondaryBackgroundColor,
            fontSize: "16pt"
        }}>
            <Flex style={{
                justifyContent: "space-between",
                alignItems: "center"
            }}>
                <Link to={"/shop"} style={{color: "black"}}><p>E-Shop</p></Link>
                <LoginProfile/>
            </Flex>
        </Header>
    )
}
