using System;
using UnityEngine;

public class Station : MonoBehaviour
{
    private const String SPRITE_ASSETS_FOLDER = "Station/";

    public int stationId;
    public Connection[] connections;

    private SpriteRenderer spriteRenderer;
    public TMPro.TMP_Text labelTmpText;

    // Start is called before the first frame update
    private void Start()
    {
        spriteRenderer = gameObject.GetComponent<SpriteRenderer>();
        if (Array.Exists(connections, x => x.transportationType == TransportationType.Subway))
        {
            spriteRenderer.sprite = Resources.Load<Sprite>(SPRITE_ASSETS_FOLDER + "station_subway");
            labelTmpText.color = Color.white;
            Debug.Log("Station " + stationId + " is subway");
        }
        else if (Array.Exists(connections, x => x.transportationType == TransportationType.Bus))
        {
            spriteRenderer.sprite = Resources.Load<Sprite>(SPRITE_ASSETS_FOLDER + "station_bus");
            labelTmpText.color = Color.black;
            Debug.Log("Station " + stationId + " is a bus");
        }
        else
        {
            spriteRenderer.sprite = Resources.Load<Sprite>(SPRITE_ASSETS_FOLDER + "station_taxi");
            labelTmpText.color = Color.black;
            Debug.Log("Station " + stationId + " is a taxi");
        }

        labelTmpText.text = stationId.ToString();
    }

    // Update is called once per frame
    private void Update()
    {
    }
}