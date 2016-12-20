var MainBox = React.createClass({
  render: function () {
    return (
      <App/>
    );
  }
});

var App = React.createClass({
  getInitialState: function () {
    return {
      data: []
    };
  },
  componentDidMount(){
    this.getDataFromServer('http://localhost:8080/luxviewer/list');
  },
  showResult: function (response) {
    this.setState({
      data: response
    });
  },
  getDataFromServer: function (URL) {
    $.ajax({
      type: "GET",
      dataType: "json",
      url: URL,
      success: function (response) {
        this.showResult(response);
      }.bind(this),
      error: function (xhr, status, err) {
        console.error(this.props.url, status, err.toString());
      }.bind(this)
    });
  },
  render: function () {
    if (this.state.data.length > 0) {
      return (
        <div>
          <FilteredList result={this.state.data}/>
        </div>
      );
    }
    
    return false;
  }
});

var FilteredList = React.createClass({

  filterList: function (event) {
    var updatedList = this.state.initialItems;
    updatedList = updatedList.filter(function (item) {
      return item.fileName.toLowerCase().search(
          event.target.value.toLowerCase()) !== -1;
    });
    this.setState({items: updatedList});
  },

  getInitialState: function () {
    return {
      initialItems: this.props.result,
      items: []
    }
  },

  componentWillMount: function () {
    this.setState({items: this.state.initialItems})
  },

  render: function () {
    return (
      <div className="filter-list">
        <input type="text" placeholder="Search" onChange={this.filterList}/>
        <List items={this.state.items}/>
      </div>
    );
  }
});

var List = React.createClass({
  render: function () {
    var result = this.props.items.map((item, i) => {
      return <ListItem item={item} key={i} onClick={this.handleClick}/>;
    });
    return (
      <ul>
        {result}
      </ul>
    )
  }
});

var ListItem = React.createClass({
  handleClick: function () {
    ReactDOM.render(
      <SubTable lineInfoList={this.props.item.lineInfoList}/>,
      document.querySelector("#sub-table")
    );
  },
  render: function () {
    return (
      <li onClick={this.handleClick}><a href="#">{this.props.item.fileName}</a></li>
    )
  }
});

var SubTable = React.createClass({
  render: function () {
    var result = this.props.lineInfoList.map(function (result, index) {
      return <SubTableItem key={index} infoList={ result }/>
    });
    return (
      <div>
        <div>
          <table>
            <thead>
            <tr>
              <th>Line</th>
              <th>Longest Word</th>
              <th>Shortest Word</th>
              <th>Average Length</th>
            </tr>
            </thead>
            <tbody>
            {result}
            </tbody>
          </table>
        </div>
      </div>
    );
  }
});

var SubTableItem = React.createClass({

  render: function () {
    var camper = this.props.infoList;
    return (
      <tr >
        <td>{camper.line}</td>
        <td>{camper.longestWord}</td>
        <td>{camper.shortestWord}</td>
        <td>{camper.averageWordLength}</td>
      </tr>
    );
  }
});

ReactDOM.render(<MainBox/>, document.getElementById('mount-point'));