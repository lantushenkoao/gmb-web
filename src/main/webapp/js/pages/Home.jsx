import React, {Component} from 'react';
import Header from '../components/Header.jsx';


class Home extends Component {
    constructor(props){
        super(props);
        this.state = {
            loggedIn: false
        }
        this.hasSessionCallback = this.hasSessionCallback.bind(this);
    }

    hasSessionCallback(){
        this.setState({loggedIn: true});
    }

    render(){
        return <div>
            <Header userLoadedCallback={this.hasSessionCallback}
                    logoutIfNoSession={false}/>
        </div>
    }
}

module.exports = Home;