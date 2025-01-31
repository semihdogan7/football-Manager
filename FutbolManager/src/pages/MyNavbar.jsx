import React, { useEffect, useRef } from 'react'

export default function MyNavbar() {

    const navbarRef = useRef(null);

    useEffect(() => {
        const updateBodyPadding = () => {
            if (navbarRef.current) {
                const navbarHeight = navbarRef.current.offsetHeight; // Navbar'ın yüksekliğini al
                document.body.style.paddingTop = `${navbarHeight - 4}px`; // Body'ye padding-top uygula
            }
        };

        updateBodyPadding();
        window.addEventListener("resize", updateBodyPadding);
        return () => {
            window.removeEventListener("resize", updateBodyPadding);
        };
    }, []);

    return (
        <nav ref={navbarRef} className={`navbar navbar-expand-lg bg-body-tertiary myNavbar`} data-bs-theme="dark">
            <div className="container-fluid">
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarTogglerDemo01">
                    <a className="navbar-brand" href="/">Futbol Manager</a>
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                        <li className="nav-item">
                            <a className="nav-link txtTitle2" aria-current="page" href="/">Ana Sayfa</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link txtTitle2" href="/league">Ligler</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link txtTitle2" href="/team">Takımlar</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link txtTitle2" href="/player">Oyuncular</a>
                        </li>



                    </ul>
                    <form className="d-flex" role="search">
                        <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search"></input>
                        <button className="btn btn-outline-warning" type="submit">Search</button>
                    </form>
                </div>
            </div>
        </nav>
    )
}
