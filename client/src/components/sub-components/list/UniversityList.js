import {Button, Image, Table} from "react-bootstrap";
import "../../../css/list.css"

const defaultImageSrc = "/images/defaultUniversities.png"

const UniversityList = (props) => {
    return (
        <Table bordered={false}>
            {props.universities && props.universities.map((university) => (
                <tr>
                    <td style={{width: 200}}>
                        <Image src={university.pic ? university.pic : defaultImageSrc} className="avatar-register"/>
                    </td>

                    <td>
                    <span>
                        {`Название: ${university.name}`}
                        <br/>
                        <br/>
                        {`Описание: ${university.description}`}
                    </span>
                    </td>
                    <td>
                        {props.variant && props.variant === 'view' && (
                            <div>
                                <Button variant="secondary" className="form-rounded">Подробнее</Button>
                            </div>
                        )}
                    </td>
                </tr>
            ))}
        </Table>
    )
}

export default UniversityList