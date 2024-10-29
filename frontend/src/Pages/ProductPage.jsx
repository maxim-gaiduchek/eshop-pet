import {useParams} from 'react-router-dom';

export function ProductPage() {
    const {id} = useParams()
    return (
        <div style={{width: "100%", maxWidth: 800, margin: "0 auto", paddingTop: 20}}>
            Product Page, id: {id}
        </div>
    )
}
