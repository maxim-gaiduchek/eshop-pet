import {Rate} from 'antd'
export function Comment() {

    return (
        <div className={"comment"}>
            <div className={"commentHeader"}>
                <div className={"commentPhoto"} style={{backgroundImage: "url(https://images.pexels.com/photos/45201/kitty-cat-kitten-pet-45201.jpeg)"}}></div>
                <div className={"info"}>
                    <span className={"commentTitle"}>Kvoza Onkay</span>
                    <br/><br/>
                    <Rate value={4} allowHalf disabled/>
                </div>
            </div>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris tincidunt tristique eros, nec luctus quam. Pellentesque eu aliquam enim, ac imperdiet magna. Aliquam fermentum, mauris a placerat rhoncus, quam lacus tincidunt metus, in lobortis turpis risus ultricies nibh. Proin nisl dolor, sollicitudin et convallis quis, mattis eu arcu.
            <div className={"commentFooter"}>
                31.12.2003
            </div>
        </div>
    )
}
