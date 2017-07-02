import React, {Component} from 'react';
import Header from '../../components/Header.jsx';
import ParametersSelector from '../../components/ParametersSelector.jsx';
import LinearChart from '../../components/LinearChart.jsx';
import moment from 'moment';


class DataChart extends Component {

    constructor(props){
        super(props);
        this.state = {
            stations : [],
            selectedStations : {
                code : 4553270,
                id: '1',
                name: '4553270 Черноморское'
            },
            fields: [],
            selectedFields: [],
            startDate: new Date(1950, 1, 1),
            endDate: new Date(1950, 3, 1),
            data: [],
            bucketsCount: 50

        };
        this.createBarChart = this.createBarChart.bind(this);
        this.onParametersChanged = this.onParametersChanged.bind(this);
    }

    onParametersChanged(name, value){
        const obj = {};
        obj[name] = value;
        this.setState(obj);
    }

    createBarChart() {
        $.ajax({
            type: 'GET',
            cache: false,
            url: '/api/data/aggregated/avg',
            data: {
                periodStart: moment(this.state.startDate).format('YYYY-MM-DD'),
                periodEnd: moment(this.state.endDate).format('YYYY-MM-DD'),
                tableName: this.state.selectedFields.map(f=>f.tableName)[0],
                columnName: this.state.selectedFields.map(f=>f.columnName)[0],
                bucketsCount: this.state.bucketsCount,
                stationCodes: this.state.selectedStations.code
            }
        }).done(data=>{
            this.setState({
                data: data
            });
        });
    }

    componentDidMount(){
        $.ajax({
            type: 'GET',
            cache: false,
            url: '/api/data/metadata/stations'
        }).done(data=>{
            this.setState({
                stations: $.map(data, (d)=> {
                    return {
                        id: d.id,
                        name: `${d.code} ${d.name}`,
                        code: d.code
                    };
                })
            });
        });
        $.ajax({
            type: 'GET',
            cache: false,
            url: '/api/data/metadata/fielddescriptions'
        }).done(data=>{
            this.setState({
                fields: $.map(data, (d)=> {
                   return {
                       id: d.id,
                       name: `${d.description} (${d.tableName} - ${d.columnName})'`,
                       tableName: d.tableName,
                       columnName: d.columnName
                   };
                })
            });
        });
    }

    render(){
        let chart = null;
        if(this.state.data.length > 0){
            chart = <LinearChart size={[1000,500]} data={this.state.data} />
        }
        return <div>
            <Header/>
            <ParametersSelector
                onParameterChanged={this.onParametersChanged}
                stations={this.state.stations}
                selectedStations={this.state.selectedStations}
                fields={this.state.fields}
                selectedFields={this.state.selectedFields}
                startDate={this.state.startDate}
                endDate={this.state.endDate}
                bucketsCount={this.state.bucketsCount}
            />
            <div className="row">
                <div className="col-lg-4">
                    <button onClick={this.createBarChart}>Показать</button>
                </div>
            </div>
            {chart}
        </div>
    }
}

DataChart.defaultProps = {
    size: [1000,500]
}

DataChart.propTypes = {
    size: React.PropTypes.array
};

module.exports = DataChart;