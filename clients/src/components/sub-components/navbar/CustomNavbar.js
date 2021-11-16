import {Container, Nav, Navbar} from "react-bootstrap";
import '../../../css/navbar.css'

const CustomNavbar = (props) => {
    return (
        <Navbar className="navbar-custom">
            <Container fluid>
                <Navbar.Brand>{props.title}</Navbar.Brand>
                <Navbar.Toggle aria-controls="navbarScroll" />
                <Navbar.Collapse id="navbarScroll">
                    <Nav
                        className="me-auto my-2 my-lg-0"
                        style={{ maxHeight: '100px' }}
                        navbarScroll
                    />
                    {props.links && props.links.map(link => {
                        return (
                            <Nav.Link key={link.name} href={link.href}>{link.name}</Nav.Link>
                        )
                    })}

                </Navbar.Collapse>
            </Container>
        </Navbar>
    )
}

export default CustomNavbar