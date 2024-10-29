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
            {/*<Flex style={{
                // height: `calc(100% - ${headerHeight}px)`,
                width: "100%",
                flexDirection: "column",
                overflowY: "auto",
                justifyContent: "center"
            }}>
                <Content style={{
                    display: "flex",
                    minHeight: `calc(100% - ${headerHeight}px - ${footerHeight}px)`,
                    width: "100%"
                }}>
                    {props.children}
                </Content>
                <ShopFooter footerHeight={footerHeight}/>
            </Flex>*/}
            <Content style={{
                display: "flex",
                minHeight: `calc(100% - ${headerHeight}px - ${footerHeight}px)`,
                width: "100%",
                overflowY: "auto",
                alignItems: "stretch"
            }}>
                {props.children}
            </Content>
            <ShopFooter footerHeight={footerHeight}/>
        </Layout>
    )
}
