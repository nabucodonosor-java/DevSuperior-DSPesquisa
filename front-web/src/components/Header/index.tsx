import React from 'react';
import { ReactComponent as Logo } from 'core/assets/images/logo.svg';
import { Link } from 'react-router-dom';
import './styles.css';

const Header = () => {

    return (
        <header className="main-header">
            <Logo />
            <Link to="/">
                <div className="logo-text">
                    <span className="logo-text-1">Big Game</span>
                    <span className="logo-text-2"> Survey</span>
                </div>
            </Link>
        </header>
    );
}

export default Header;