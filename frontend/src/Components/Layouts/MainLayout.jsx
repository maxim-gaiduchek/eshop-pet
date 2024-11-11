import {Layout} from "antd";
import {Content} from "antd/lib/layout/layout";
import {ShopHeader} from "./Components/ShopHeader";
import {ShopFooter} from "./Components/ShopFooter";

export function MainLayout(props) {
    const headerHeight = 100;
    const footerHeight = 50;
    return (
        <Layout style={{height: "100dvh"}}>
            <ShopHeader headerHeight={headerHeight}/>
            <Content style={{
                display: "flex",
                minHeight: `calc(100% - ${headerHeight}px - ${footerHeight}px)`,
                width: "100%",
                overflowY: "auto",
            }}>
                {props.children}
            </Content>
            <ShopFooter footerHeight={footerHeight}/>
        </Layout>
    )
}
